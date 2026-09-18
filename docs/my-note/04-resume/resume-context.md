# 简历项目上下文（跨会话交接文档）

> 用途：新会话窗口读取本文档即可了解全部历史背景，继续修改操作。
> 最后更新：2026-09-18（Day3 笔记全面重写补全；新增第 8 节笔记整理经验）
> 当前工作模式：用户直接在 OA 项目（D:\project\java\yqg_oa）用 IDEA Kimi 提问，把本文档喂给它即可，无需切回本仓库。

---

## 1. 个人情况

- 95 年男，211 计算机硕士（北京林业大学 软件工程 2020.09-2023.06，**非全日制**，简历上不标注，面试口径：边工作边读）
- 本科：贵州师范学院 特殊教育（2013-2017），跨专业
- 工作经历：2021.08-2022.03 脉脉（实习）→ 2022.03-2023.03 滴滴（实习）→ 2023.03-2026.04 瓴岳科技/洋钱罐金融（正式，Java 后端）
- 简历写"工作年限 5 年"（用户决定：2021 年起边工作边读非全，实习与学业重叠，不单独标注实习）
- 求职意向：Java 后端开发工程师（北京）；2024、2025 连续两年 A 档绩效
- 已离职（2026.04），空窗期口径需准备

## 2. 关键飞书文档

| 文档 | 链接 | 说明 |
|---|---|---|
| 简历 v1（已定稿 ✅） | https://my.feishu.cn/wiki/CoLcwykKQiDjwrkhH3MckIIrnhd | document_id: CoLcwykKQiDjwrkhH3MckIIrnhd，可直接投递 |
| 项目初步整理 | https://my.feishu.cn/wiki/FlsnwTFqIilyrZkBuPrcHvu3nBb | 新版本 h1 block_id: AxX0dnGImo49gvxrG9Qc4tjNnjb；含修改建议+定稿版+弹药库 |
| 面试口述 | https://my.feishu.cn/wiki/OL08wgBQLiDQGaklj5Ccx2jpnYf | ✅ 2026-09-15 已写入三部分：一、自我介绍模板（1 分钟版默认开场 + 3 分钟版展开）；二、项目经历简述（一句话电梯版 + Crane/OA 各 1.5 分钟详述版）；三、开场高频追问口径（离职空窗⚠️口径待定/非全学历/个人优势） |
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

## 4. V1 简历定稿内容摘要

- **个人技能 15 条**：精通并发、精通 JVM（含 Arthas/jstack/jmap）、熟练 Java 基础/算法设计模式/MySQL/Redis、熟练 Spring 系、**了解** SpringCloud Alibaba（已降级，项目无使用）、熟悉分布式方案（YJob/XXL-Job）/Kafka/权限系统/网络、工具链、了解 AI Agent、熟练 Cursor/Claude Code
- **项目经验**：Crane（独立负责，4 条 bullet：审批流三层架构/并发与一致性/查询三代演进/生命周期自动化）+ OA（深度参与，4 条 bullet：异动审批流 0 到 1/生效同步与字段改造/Moka 双向对接/自动入职与数据同步）
- **自我评价**：代码规范 + 连续两年 A 档绩效（健身那条已删）

## 5. 待办与风险清单

| 优先级 | 事项 | 状态 |
|---|---|---|
| 中 | Kafka 八股整理（目前参考模板描述） | 未开始 |
| 面试前 | 必背口径 5 个（离职空窗/非全学历/SpringCloud 追问/最难问题/优势）——面试口述文档第三部分已有模板，空窗口径待定稿 | 部分完成 |

## 6. V2 版本目标（AI 方向）

- 现状：V1 仅技能栏 2 条 AI（了解 Agent 概念 + 熟练 AI 工具），项目经验无 AI
- 待整理Agent项目，V1 中项目经历部分（Crane + OA）已全部梳理完成，可开始 V2
- 2026-09-16 进展：AI Agent 项目（BitGuide Agent Platform，飞书说明父目录 https://ecnz1eq1tqym.feishu.cn/wiki/EskgwYLsHiaZnYkKk8OcLRujn1F）已扫描完毕；7 天学习计划已写入 https://my.feishu.cn/wiki/QQqWw7LlfiAvfukoODdcCeLynWf（Day1-3 读 ch01-ch06 概念与设计，Day4-6 ch07 Java 实战跑通三阶段+4 个递进练习，Day7 ch09a/b/c 面试冲刺+简历段落定稿）。⚠️ ch09b 简历模板明确警告不可照搬，项目名需改。扩展篇 Stage4/Stage5 不在 7 天计划内，可作第二周补充
- 2026-09-18 进展：Day3 笔记（https://my.feishu.cn/wiki/LzJswWlKfi1QG6kBzdZczvExnPd）已按原始 ch05/ch06 文档全面重写补全（rev 38，16.8K→44.7K 字符，43 代码块+13 表格）。ch05 部分补：stage2.yml 完整 YAML、字段说明表、etcdctl 实操、etcd 存储设计表、冷热重启对比表、健康检查表、新增 1.6 CLI 交互/1.7 全链路追踪（3 场景）/1.8 可观测性（原 1.6 小结改 1.9）；ch06 部分补：复用/新增能力表、Gateway 位置图+process 伪码、Filter 接口+执行顺序、API Key 配置、三重校验器、令牌桶+两级限流+单机vs分布式表、Lane 冲突示例+四种策略行为、DistributedMemory 读写、API 表+请求/响应 JSON+Web 前端表、SIGTERM+K8s 滚动更新、架构数据流总图、新增 2.12 可观测性（原 2.12 改 2.13）。中间产物在 note-up/.tmp/（ins_01~22.xml 插入内容、day3_v2.json 最终版）

## 7. 工具环境备忘

- 读飞书文档：`lark-cli docs +fetch --doc <url> --as user`；改文档：`docs +update --command str_replace|block_replace|block_insert_after|append --as user`；多行内容用 `--content @./file`（相对 CWD）；文末插入用 `--block-id -1`
- Windows bash 输出中文需 `PYTHONIOENCODING=utf-8 python -c ...` 处理 JSON
- 本目录其他文件：resume.txt（任务入口）、个人情况.txt、八股文整理背景.txt、项目经验.txt
- 中间产物备份：note-up/.tmp/ 下有 changes_report.md（异动模块调查报告）、moka_report.md（Moka 调查报告）、oa_1~6_*.xml（背诵手册 OA 章节源稿），可追溯原始行号与完整代码

## 8. 笔记整理经验（学习笔记质量标准，2026-09-18 Day3 重写总结）

**核心教训：不能过度省略。** 学习笔记是用来背诵和面试复盘的，只写结论性文字、省略代码块和配置，复习时无法还原细节，等于白记。Day3 第一版就是反面教材（16.8K 字符，配置全靠一句话概括），被迫对照原文重写（补到 44.7K、43 代码块+13 表格）。

### 必须齐全的内容清单（以后整理笔记逐条对照）

1. **配置文件必须给全文**：如 stage2.yml 的 agents+models 完整 YAML，不能只写"配置字段有 id/modelRef/..."一句话；字段说明另配表格
2. **命令实操必须可复现**：如 etcdctl put 的完整命令+JSON body，不能只写"往 etcd 推配置"
3. **核心流程必须给伪码/时序**：如 Gateway.process() 全流程、AgentRegistry 结构、SIGTERM 关闭步骤、T0-T13 全链路追踪，不能只写步骤摘要列表
4. **对比维度必须列表格**：冷热重启对比、单机vs分布式限流、四种 Lane 策略等，表格比一段文字好背
5. **示例必须具体**：注入攻击示例、并发冲突示例、请求/响应 JSON 示例，一个具体例子胜过三段描述
6. **易遗漏的固定章节**：CLI/交互演示、全链路数据流追踪（多场景）、可观测性埋点（指标+告警）——原始文档的这三类章节第一版全丢了

### 工作方法（重写/补全流程）

1. 先 `docs +fetch` 原始文档和笔记全文（--detail with-ids），逐节 diff 找出省略点，列成清单再动手
2. 保留笔记原有骨架（章节编号、口诀、面试话术、高亮重点），只补缺不重写——用 `block_insert_after` 按节插入，不整体 overwrite（会丢图片和评论）
3. 插入内容写成 XML 文件（.tmp/ins_*.xml），同一锚点插多块时注意后插的在前；写完统一跑脚本执行并检查每步 `ok:true`
4. 新增章节导致编号变化时，用 `str_replace` 改后续章节号（如 1.6→1.9、2.12→2.13）
5. 最后必须验证：重新 fetch 全文，检查关键内容点全部命中 + 章节顺序正确，不能只信 update 返回的 success
6. 中间产物（插入 XML、最终 fetch JSON）留在 .tmp/ 备查
