*** Settings ***
Library   SeleniumLibrary

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

