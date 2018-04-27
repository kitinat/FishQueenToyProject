*** Settings ***
Library   SeleniumLibrary
Suite Teardown  Close All Browsers

*** Test Cases ***
Search All
  Open search page
  Search
  Should display result 31 products

Select product 43 piece dinner set
  Open search page
  Select Female and 3-5
  Search
  Should display result 10 products
  Select 43 piece dinner set product
  Input quantity   2
  Click add to cart

Click cart and checkout
  Check quantity
  Check total
  Click process to checkout
  Add shipping detail and deliver


*** Keywords ***
Open search page
  Open Browser   http://128.199.66.209:8080/index.html  browser=chrome

Search
  Click Element   id:btnSearch

Should display result 31 products
  Wait Until Element Contains   id:searchResult   31 Items Found
  Capture Page Screenshot

Select Female and 3-5
  Select From List By Value   id:age   3
  Select From List By Value   id:gender   2

Should display result 10 products
  Wait Until Element Contains   id:searchResult   10 Items Found
  Capture Page Screenshot

Select 43 piece dinner set product
  Click Element   id:toy_2
  Capture Page Screenshot

Input quantity
  [arguments]  ${qty}
  Press Key   id:toy_qty   \\08
  Press Key   id:toy_qty   ${qty}
  Mouse Out   id:toy_qty
  Click Element   id:toy_total_price
  ${total} =	Execute JavaScript	return $("#toy_total").val()
  Should Be Equal	${total}	43 Piece dinner Set x 2 items
  ${sum} =	Execute JavaScript	return $("#toy_total_price").val()
  Should Be Equal	${sum}	12.95 x 2 = 25.90 THB
  Capture Page Screenshot

Click add to cart
  Click Element   id:btn_add_cart
  Capture Page Screenshot

Check quantity
  Wait Until Element Contains   id:2   2

Check total
  Wait Until Element Contains    id:subtotal   25.90
  Capture Page Screenshot


Click process to checkout
  Click Element  id:btn_process
  Click Element  class:btn btn-chelsea


Add shipping detail and deliver
  Input Text   id:fullname   นายประธาน ด่านสกุลเจริญกิจ
  Input Text   id:email   fishqueentoy@gmail.com
  Input Text   id:address1   เลขที่3 อาคารพร้อมพันธุ์ 2 ห้อง 1001 ชั้น 10
  Input Text   id:address2   ซอยลาดพร้าว 3
  Input Text   id:city   จตุจักร
  Input Text   id:province กรุงเทพ
  Input Text   id:post_code 10900
  Capture Page Screenshot
  Click Element   id:btn_add_cart
  Click Element   id:confirm
  Capture Page Screenshot