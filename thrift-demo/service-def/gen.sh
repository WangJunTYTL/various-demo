rm -rf src/main/java/*
thrift --out src/main/java -r --gen java hello.thrift
