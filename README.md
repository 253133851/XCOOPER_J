# XCOOPER_J
xcooper 服务器端

### -接口说明

> COMMENT 评论

1. 

### list 清单

> ##### 添加清单 AddList

1.url: `../do?action=list!CAjaxAddList`

2.参数说明:

| 参数          | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| listName       | 清单名      | String    |           | 
| projectId | 项目id      | int | 所属项目的id          | 
| orderNum | 排序值      | int    |  排序值越大越靠前   |

3.返回值

| 参数        | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| response      |               | String   | "ok"成功否则失败    | 

> ##### 删除清单 DeleteList

1.url: `../do?action=list!CAjaxDeleteList`

2.参数说明:

| 参数          | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| id      | 清单id      | int    | 根据id删除          | 

3.返回值

| 参数        | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| response      |               | String   | "ok"成功否则失败    | 



>##### 更新清单 UpdateList

1.url: `../do?action=list!CAjaxUpdateList`

2.参数说明:

| 参数          | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| id       | 清单id      | int    |           | 
| listName | 清单名      | String |           | 
| orderNum | 排序值      | int    |  排序值越大越靠前   |

3.返回值

| 参数        | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| response      |               | String   | "ok"成功否则失败    | 

>##### 查询某一个projectId下的清单 QueryListByProjectId

1.url: `do?action=list!CAjaxQueryListByProjectId`

2.参数说明:

| 参数          | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| projectId      | 项目id     |  int  |           | 


3.返回值

| 参数        | 参数名           | 参数类型  | 说明      |
| ------------- |:-------------:| -----:| ------------:|
| json      |   返回结果             | json   | 返回指定列    | 
| error      |   错误          | json   |  "该projectId下找不到内容"  | 