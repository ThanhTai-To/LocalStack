# LocalStack
run (on folder resources): 
docker-compose -f localstack.yml up
run: 
aws --endpoint-url=http://localhost:4566 s3 mb s3://upload
