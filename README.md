# LocalStack

run (on folder resources): 

docker-compose -f localstack.yml up

run: 

aws --endpoint-url=http://localhost:4566 s3 mb s3://upload


Intellij Idea: maven tab -> compile -> Run Maven Build

Run App 

// Checking
aws --endpoint-url=http://localhost:4566 s3 ls