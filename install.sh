#!/bin/bash

sh ./build.sh
mkdir ~/.syi-mock
cp release/syi-mock.jar ~/.syi-mock
cp syi-mock.sh ~/.syi-mock

ln -s ~/.syi-mock/syi-mock.sh /usr/local/bin/syi-mock