#!/usr/bin/env bash

wget "https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-linux64.tar.gz"
wget "https://chromedriver.storage.googleapis.com/2.35/chromedriver_linux64.zip"

unzip *.zip
tar -zxf *.gz

rm *linux64*

mv  *driver src/main/resources/