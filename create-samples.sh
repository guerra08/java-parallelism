#!/bin/sh
mkdir -p $HOME/tmp/sample-files
echo "Creating sample files for testing..."
for num in $(seq 1 500); do
  base64 /dev/urandom | head -c $(shuf -i 2621440-2936012 -n 1) >> $HOME/tmp/sample-files/file${num}.txt;
done