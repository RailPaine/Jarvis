1. 首页不跳新界面（首页使用三个framgent）

2. 确定app主色调。 应用范围和功能  确定科技蓝 使用material design  light blue色调。
4. 界面改版。
5. 事件拆解规划。
6. 提交入各大手机市场。
7. 任务类型：每日任务、每周任务、普通任务等 花费类型：每次花费，一次花费等。
8. 已完成任务 功能。
9. 云端数据存储。
10. 统计功能，即每日统计，每周统计等。
11. 删除任务，花费。
12. 编辑任务，花费。
3. 规范代码并归档。  
* 项目工程文件分类。
根据activity、bean、util分类
总结 activity文件规则，并严格审查。
总结 布局文件规则，并严格审查。
* 总结 静态变量文件规则，并严格审查。
分为两类：只有当前class会用，状态常量，注释即可。
全局使用，
Bundle常量  Aa2Ba_Name;
SPkey常量  无（默认常量）
REQ_RESULT常量  REQ_Aa2Ba_Name;RESULT_Ba2Aa_Name;
URL常量 Aa_Name;

* 总结 string文件，color文件规则，并严格审查。
值唯一，键唯一。提交前，lint检查，清除无效资源。
