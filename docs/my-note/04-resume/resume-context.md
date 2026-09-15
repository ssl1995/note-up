# 简历项目上下文（跨会话交接文档）

> 用途：新会话窗口读取本文档即可了解全部历史背景，继续修改操作。
> 最后更新：2026-09-15（OA 项目 STAR 背诵手册输出完成，Crane + OA 均已梳理完毕）
> 当前工作模式：用户直接在 OA 项目（D:\project\java\yqg_oa）用 IDEA Kimi 提问，把本文档喂给它即可，无需切回本仓库。

---

## 1. 个人情况

- 95 年男，211 计算机硕士（北京林业大学 软件工程 2020.09-2023.06，**非全日制**，简历上不标注，面试口径：边工作边读）
- 本科：贵州师范学院 特殊教育（2013-2017），跨专业
- 工作经历：2021.09-2022.03 脉脉（实习）→ 2022.03-2023.03 滴滴（实习）→ 2023.03-2026.04 瓴岳科技/洋钱罐金融（正式，Java 后端）
- 简历写"工作年限 5 年"（用户决定：2021 年起边工作边读非全，实习与学业重叠，不单独标注实习）
- 求职意向：Java 后端开发工程师（北京）；2024、2025 连续两年 A 档绩效
- 已离职（2026.04），空窗期口径需准备

## 2. 关键飞书文档

| 文档 | 链接 | 说明 |
|---|---|---|
| 简历 v1（已定稿 ✅） | https://my.feishu.cn/wiki/CoLcwykKQiDjwrkhH3MckIIrnhd | document_id: CoLcwykKQiDjwrkhH3MckIIrnhd，可直接投递 |
| 项目初步整理 | https://my.feishu.cn/wiki/FlsnwTFqIilyrZkBuPrcHvu3nBb | 新版本 h1 block_id: AxX0dnGImo49gvxrG9Qc4tjNnjb；含修改建议+定稿版+弹药库 |
| 口述整理 | https://my.feishu.cn/wiki/OL08wgBQLiDQGaklj5Ccx2jpnYf | 五部分：Crane/OA 问答、技能速测、必背口径、自查表 |
| AI Agent 概念笔记 | https://my.feishu.cn/wiki/Y9hcw9UI6iiM8FkjbYNcQPTunGc | V2 素材来源 |
| 项目背诵手册 | https://my.feishu.cn/wiki/D3gcwTaekii186kHfQNcW8RVnpc | h1【Crane】✅ + h1【OA】✅（2026-09-15 完成）均按 STAR 梳理。OA 章节结构：概述 → 模块一 其他异动审批流 0 到 1（动态审批人/驳回重提排序）→ 模块二 生效同步与业务管理者字段改造 → 模块三 Moka 审批流双向对接（幂等）→ 模块四 自动入职与数据同步 → 数据库表模式。注意：STAR 原则章节已被用户手动删除，章节从"二、"开始编号 |

## 3. 本地代码库（简历事实来源）

| 项目 | 路径 | 说明 |
|---|---|---|
| Crane 权限中台 | D:\project\java\crane-backend | 用户独立负责；文档在 docs/crane-doc（01-15） |
| OA 系统 | D:\project\java\yqg_oa | 团队协作，用户深度参与（git 账号 songshenglin，87 次提交排第二）；文档在 doc/oa-knowledge |

### Crane 代码证据（写简历/面试追问的事实依据）
- 权限申请入口：`crane-admin/.../controller/internal/permission/PermissionApplyController.java`
- 核心服务：`crane-core/.../service/impl/RoleApplyServiceImpl.java`（L269 submitRoleFlow / L473 flowCallBack / L597 openEmployeeRelationRole）
- 三层架构：`PermissionApplyFlowFactory` + `AbstractPermissionApplyFlow`（@PostConstruct 自注册，4 种申请类型路由）
- 分布式锁：`crane-core/.../lock/CommonLock.java` + `CommonLockAspect.java`（Redisson，粒度=工号+业务ID）
- 查询优化：`EmployeeAuthorityServiceImpl`（V1 串行→V2 CompletableFuture+TTL→V3 批量合并）、`EmployeeRedisCache`（256 桶分桶+Snappy）、`FetchAuthorityThreadContext`
- 技术栈事实：SpringBoot 1.5 + JDK8 + MyBatis + MySQL8 + Redis/Redisson + Kafka + MongoDB + Feign(自研注解) + YJob 120+ 定时任务；**无 SpringCloud 组件**
- 业务量口径：日均 RPC **十万级**（用户已将百万级改为十万级，全篇统一）

### OA 代码证据（员工异动模块）
- 核心服务：`oa-core/.../employee_changes/other/EmployeeChangesOtherServiceImpl.java`（1743 行：L165 doSubmitOne 发起 / L274 updateAndResubmit 重提 / L973 doTakeChangesEffect 生效 / L1100 updateEmployeeByBusiness 企微同步 / L697 印尼校验）
- 回调：`EmployeeChangesOtherCallback.java`（L105 callbackBusiness / L265 updateBusinessByFormString 表单回写 / L392 splitStartCircleStep 动态节点钩子）
- 动态审批人：`oa-core/.../employee/FetchEmployeeLeadersService.java` L121-188 fetchEmployeeChangesOtherDynLeaders（>I3 必到 M5；≤I3 按直属/二级上级职级分档）；L202-238 汇总去重规则（异动后链→异动前HRBP→异动前链）
- 驳回重提排序修复：`AbstractEmployeeChangesFlowCallback.sortIndexLevelList` L153-181（REQUEST_CHANGE 按 level 正序；git `653527e2ba`，D338973）
- 延迟生效 Job：`oa-scheduler/.../jobs/EmployeeChangesAutoEffectJob`（三重筛选：未生效+到期+未离职）+ `EmployeeChangesSubEffectParentJob`（等子流程回写）
- 关键 git 提交：2023-12-17 `44ed172b35` 发起流程（0→1 起点）；业务管理者改造链 `5e4dc45fae`→`fe8be4e36e`→`9ad31a53c5`→`ad01a45b70`（印尼校验）

### OA 代码证据（Moka 集成模块）
- 核心服务：`oa-core/.../service/moka/MokaServiceImpl.java`（L226 submitOrResubmitFlow 幂等 / L1375 autoEntryV2 自动入职 / L1563 getMokaV2Data 游标分页 / L1483 面试反馈待办 businessId=-2）
- 回调与状态映射：`MokaFlowCallBack.java`（按 flowCode 路由 MRF/MOF/MJF）、`MokaRecruitStatus` / `MokaApproveOfferStatus`（OA↔Moka 双向映射枚举，@ThirdPartyEnum）
- 13 个 public-api 端点：`oa-api/.../public_controller/PublicMokaController.java`（/public-api/v1，@AccessOpen）
- 自动入职 Job：`oa-scheduler/.../jobs/moka/MokaAutoEntrySubmitV2Job.java`；双数据源：`MokaCandidateStateToOaJob`（V2 + 人才库手机号反查）
- 429 修复：`MokaTalentPoolCandidateToOaJob` Thread.sleep + `MokaAutoEntryInfoConfig.threadSleep=1000`（配置化）；git `98a4bc3496`（2024-03-15）、幂等优化 `ac278feafb`（2024-01-12 oncall 触发）
- 幂等中枢表：`sync_out_history` 四元组唯一索引 uindex__biz_third_type（V584）
- OA 技术栈：SpringBoot + SpringCloud **Eureka** + JOOQ + MySQL + Redis/Redisson + Kafka + ES + YJob
- ⚠️ 勿写：年度晋升批量异动、下级联动异动（employee_changes_sub）、会议室时间段运算（无用户提交记录）
- ⚠️ 已知存疑点：`EmployeeChangesOtherCallback` L231 疑似 bug（beforeVo 取的是 afterVo），面试被深挖详情页权限时注意口径

## 4. V1 简历定稿内容摘要

- **个人技能 15 条**：精通并发、精通 JVM（含 Arthas/jstack/jmap）、熟练 Java 基础/算法设计模式/MySQL/Redis、熟练 Spring 系、**了解** SpringCloud Alibaba（已降级，项目无使用）、熟悉分布式方案（YJob/XXL-Job）/Kafka/权限系统/网络、工具链、了解 AI Agent、熟练 Cursor/Claude Code
- **项目经验**：Crane（独立负责，4 条 bullet：审批流三层架构/并发与一致性/查询三代演进/生命周期自动化）+ OA（深度参与，4 条 bullet：异动审批流 0 到 1/生效同步与字段改造/Moka 双向对接/自动入职与数据同步）
- **自我评价**：代码规范 + 连续两年 A 档绩效（健身那条已删）

## 5. 待办与风险清单

| 优先级 | 事项 | 状态 |
|---|---|---|
| ⚠️ 高 | Redis 八股补课（持久化/主从哨兵/大key热key），技能栏写"熟练掌握"但无整理文档 | 未开始 |
| 中 | 查询接口 V1→V3 的 TP99/耗时数字，回忆后补入简历 bullet 3 | 未补 |
| 中 | Kafka 八股整理（目前参考模板描述） | 未开始 |
| 面试前 | 必背口径 5 个（离职空窗/非全学历/SpringCloud 追问/最难问题/优势）——复习手册第四部分已有参考答法 | 待背 |

## 6. V2 版本目标（AI 方向）

- 现状：V1 仅技能栏 2 条 AI（了解 Agent 概念 + 熟练 AI 工具），项目经验无 AI
- 待整理Agent项目，V1 中项目经历部分（Crane + OA）已全部梳理完成，可开始 V2

## 7. 工具环境备忘

- 读飞书文档：`lark-cli docs +fetch --doc <url> --as user`；改文档：`docs +update --command str_replace|block_replace|block_insert_after|append --as user`；多行内容用 `--content @./file`（相对 CWD）；文末插入用 `--block-id -1`
- Windows bash 输出中文需 `PYTHONIOENCODING=utf-8 python -c ...` 处理 JSON
- 本目录其他文件：resume.txt（任务入口）、个人情况.txt、八股文整理背景.txt、项目经验.txt
- 中间产物备份：note-up/.tmp/ 下有 changes_report.md（异动模块调查报告）、moka_report.md（Moka 调查报告）、oa_1~6_*.xml（背诵手册 OA 章节源稿），可追溯原始行号与完整代码
