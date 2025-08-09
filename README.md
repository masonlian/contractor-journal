**這是一份以建築承包商為背景環境參照的企業後端，
內容包含案場功能、日曆功能、人事與成本功能、用戶功能、財務與多媒體功能！** 
<br>

**使用了下列技術:<br>**
**-Java 17 + Spring Boot + Spring Security + JWT + MySQL + Spring JDBC**


<br>
<br>

－案場功能： 案場資訊操作、報價單操作、收款明細操作，自動計算案場財務狀況。<br>
－日曆功能：當日案場資料操作、排班操作與出缺席確認、彈性計算工期。<br>
－成本功能：物料資料操作、物料採購操作以及款項狀態確認。<br>
－人事成本：人事資料操作、出席資料操作、薪酬資料操作。<br>
－財務功能：統整上述四種功能中相關財務表現加以計算後以季度為單位紀錄資料並提供調閱。<br>
<br>

－用戶功能: 使用者資料操作，結合Jwt與Spring Security技術將敏感資訊遮罩並且根據用戶權限分配能夠操作的功能。<br>

小結：<br>
使用者可以透過建立報價單、收付款項、排班以及物料採購等事件“間接”計算特定案場的財務狀況，以收支平衡的的形式隨著事件的持續變動更新平衡的數目，集合所有案場後得到整間公司的財務表現，其中表單皆以加註狀態確金額能被正確交付。 此外，使用者能夠根據專案需要上傳與下載如藍圖等影像資料或其他重要訊息，並根據已被歸類的施工項目得到有幫助的資源或相關法規警示。





<br>
<br>

**下列表格為API整理內容！**
<br>

| 資源 | 路徑      | 方法 | 用途 | 使用角色 |  |   | 
| --- | --- | --- | --- | --- | --- | --- |
|  |  |  |  |  |  |  |
| 專案 | `/projects` | POST | 創建專案 | L0、L1 |  |  |
| 專案 | `/projects/{projectid}` | DELETE | 刪除專案 | L0、L1 |  |  |
| 專案 | /projects | GET | 取得案件 | L0、L1 |  |  |
| 專案 | `/projects/{projectId}/quotation` | POST | 創建估價單 | L0、L1 |  |  |
| 專案 | `/projects/{quotationId}/item` | POST | 創建估價單內容 | L0、L1 |  |  |
| 專案 | `/projects/{projectId}/quotation/` | GET | 取得特定專案估價單 | L0、L1 |  |  |
| 專案 | `/projects/received` | POST | 建立收款明細 | L0、L1 |  |  |
| 專案 | `/projects/{projectId}/received` | GET | 取得收款明細 | L0、L1 |  |  |
| 日曆 | `/calendar/event` | POST  | 建立日曆事件 | L0、L1 |  |  |
| 日曆 | `/calendar/event/{eventId}` | DELETE | 刪除日曆事件 | L0、L1 |  |  |
| 日曆 | `/calendar/event/{eventId}` | PUT | 更新日曆事件 | L0、L1 |  |  |
| 日曆 | `/calendar`  | GET | 取得特定日期事件 | L0、L1、L2 |  |  |
| 日曆 | `/calendar/event/{eventId}/labor` |  POST | 創建當日排班 | L0、L1 |  |  |
| 日曆 | `/calendar/{eventId}/labor` | GET | 取得出席人員 | L0、L1、L2、L3 |  |  |
| 日曆 | `/calendar/{eventId}/attendance` | PUT | 出席人員打卡 | L0、L1、L2、L3 |  |  |
| 日曆 | `/calendar/event/{eventId}` | PUT | 結束專案 | L0、L1 |  |  |
| 成本管控 | `/costmgmt/material` | POST | 建立物料清單 | L0、L1、L2 |  |  |
| 成本管控 | `/costmgmt/material/{materialId}` | DELETE | 刪除物料種類 | L0、L1、L2 |  |  |
| 成本管控 | `/costmgmt/material/{materialId}` | PUT | 更新物料種類 | L0、L1、L2 |  |  |
| 成本管控 | `/costmgmt/material"` | GET | 取得物料 | L0、L1、L2、L3 |  |  |
| 成本管控 | `/costmgmt/construction` | POST | 創建施工項目 | L0、L1、L2 |  |  |
| 成本管控 | `/costmgmt/construction/{constructionId}` | DELETE | 刪除施工項目 | L0、L1、L2 |  |  |
| 成本管控 | `/costmgmt/construction` | GET | 取得施工項目 | L0、L1、L2、L3 |  |  |
| 成本管控 | `/costmgmt/construction/{constructionId}` | PUT | 更新施工項目 | L0、L1、L2 |  |  |
| 成本管控 | `/payable/{payableId}` | PUT | 確認付款給廠商 | L0、L1 |  |  |
| 成本管控 | `/payable` | GET | 取得付款明細 | L0、L1 |  |  |
| 人事資料 | `/employee` | POST | 創建人事資料 | L0、L1、、L2、L3 |  |  |
| 人事資料 | `/employee` | GET | 取得人事資料 | L0、L1 |  |  |
| 人事資料 | `/employee/{employeeId}` | PUT | 更新人事資料 | L0、L1 |  |  |
| 人事資料 | `/employee/{employeeId}` | DELETE | 刪除人事資料 | L0、L1 |  |  |
| 人事資料 | `/employee` | GET | 取得薪資 | L0、L1 |  |  |
| 使用者 | `/users/login` | POST | 使用者登入 | L0、L1、L2、L3 |  |  |
| 使用者 | `/users/register` | POST | 創建使用者 | L0、L1、L2、L3 |  |  |
| 公司經營 | `/financial/{quarter}` | GET | 取得財務表現 | L0 |  |  |
| 成本管控 | /material/event | POST | 創建物料事件 | L0、L1 |  |  |

<br>
<br>

**下列API測試結果！**
<br>


| 測試內容               | 測試項目             | 測試輸入                     | 預期回傳結果                                                                                      | 說明                                     |
|------------------------|----------------------|------------------------------|----------------------------------------------------------------------------------------------------|------------------------------------------|
| Project POST測試       | 創建承包專案         | 詳見附錄A腳本                | 201Created +專案資料                                                                                | 正常                                     |
| Calendar PUT測試       | 結束專案             | 輸入結束布林值               | 200Success +專案資訊                                                                                | 正常                                     |
| Calendar POST測試      | 非規定打卡時間       | 輸入非當日時間               | 406Not Acceptable t+ 非上班時間                                                                     | 驗證時效正確                             |
| Calendar PUT測試       | 員工出席打卡         | 登入資訊與出席布林值         | 200 Success +打卡時間                                                                               | 記錄打卡時間與實際薪資                   |
| Calendar POST測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Calendar POST測試      | 錯誤請求欄位         | 輸入錯誤出席人員             | 400Bad Request+ 該員工不存在                                                                        | 驗證欄位檢查                             |
| Calendar POST測試      | 創建排班人員         | 輸入出席人員                 | 201Create +出席人員資料                                                                             | 預估人事成本加入計算並自動建立簽到表     |
| Calendar POST測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Calendar POST測試      | 時間欄位不符         | 錯誤的時間格式               | 400 BadRequest                                                                                      | 驗證格式符合                             |
| Calendar POST測試      | 創建日曆事件         | 施工事件排程                 | 201Created+施工資料                                                                                 | 根據排程計算成本與工期                   |
| Project POST測試       | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Project POST測試       | 缺失請求欄位         | 缺失收款人欄位               | 400 Bad Request                                                                                     | 驗證欄位檢查                             |
| Project POST測試       | 建立收款紀錄         | 收款細部資料                 | 201Created+專案資料                                                                                 | 將收款納入財務計算                       |
| Project POST測試       | 權限不足             | 登入權限無法創建報價單與內容 | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Project POST測試       | 創建報價單內容       | 詳見附錄Ａ腳本               | 201 Created +報價內容                                                                               | 正常                                     |
| Project POST測試       | 創建專案報價單       | 詳見附錄A腳本                | 201 Created +報價單概述                                                                             | 正常                                     |
| Project POST測試       | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Project POST測試       | 缺失請求欄位         | 缺失專案欄位名稱             | 400 Bad Request                                                                                     | 驗證欄位檢查                             |
| Material POST測試      | 創建物料清單         | 詳見附錄A資料                | 201Create +回傳資料                                                                                 | 正常                                     |
| Material PUT測試       | 付款給供應商         | 付款資訊                     | 200 Success 付款後狀態 (詳見 Notion 附錄)                                                          | 正常                                     |
| Material POST測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Material POST測試      | 缺失請求欄位         | 缺失採購物料專案欄位         | 400 Bad Request                                                                                     | 驗證欄位檢查                             |
| Material POST測試      | 創建採購清單         | 採購物料列表                 | 201Created  +採購明細                                                                               | 將採購物料納入財務計算並創建應付款項表單 |
| Material POST測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Material POST測試      | 缺失請求欄位         | 缺失職責欄位                 | 400 Bad Request                                                                                     | 驗證欄位檢查                             |
| HumanResource POST測試 | 人力資源建檔         | 詳見附A資料                  | 201Create + 回傳資料                                                                                | 正常                                     |
| Material POST測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Material POST測試      | 缺失請求欄位         | 缺失物料單一欄位名稱         | 400 Bad Request                                                                                     | 驗證欄位檢查                             |
| Project GET測試        | 取得專案             | limit=5&offset=5             | 200 Success+專案資料                                                                                | 檢驗頁面大小跟偏移量                     |
| Calendar GET測試       | 取得日曆事件         | 輸入特定日期                 | 200 Success +日曆事件                                                                               | 取得特定日期所有日曆事件                 |
| Project GET測試        | 取得收款明細         | 輸入專案ID取得業主支付款項   | 200 Success ＋收款紀錄                                                                              | 正常                                     |
| Project GET測試        | 取得專案報價單       | 輸入專案ID根據需要取得報價單 | 200Success +報價單資料                                                                              | 正常                                     |
| Project GET測試        | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Project GET測試        | 取得專案             | search = 連先生              | 200 Success ＋專案資料                                                                              | 檢驗專案人名搜索功能                     |
| HumanResource GET測試  | 取得員工班次紀錄     | 員工姓名                     | 200 Success + 該員出席紀錄                                                                          | 正常                                     |
| Material GET測試       | 取得廠商付款資訊     | 廠商姓名或其他特定搜索條件   | 200Success +付款紀錄（含搜尋限制條件）                                                             | 驗證搜索條件是否有效                     |
| Financial GET測試      | 權限不足             | 登入權限不足用戶傳送請求     | 403 Forbidden                                                                                       | 驗證權限檢查                             |
| Financial GET測試      | 取得季度財務狀況     | 季度三                       | 200 Success ＋第三季財務狀況                                                                        | 正常                                     |
| Material GET測試       | 取得專案採購物料清單 | 專案ID                       | 200Success + 採購紀錄                                                                               | 正常                                     |
