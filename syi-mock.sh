#!/bin/bash

java -jar ~/.syi-mock/syi-mock.jar
echo -e "Item id: "
read item
echo -e "Category id: "
read category
java -jar ~/.syi-mock/syi-mock.jar $item $category
