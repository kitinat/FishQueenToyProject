*** Settings ***
Library  SeleniumLibrary

*** Test cases ***
Search All
  Open search page
  Click 'Search' button
  Should display total 31 products on screen

*** Keyword ***
Search
  Click Element
Open search page
  Open browser  http://128.199.66.209:8080/index.html browser=chrome