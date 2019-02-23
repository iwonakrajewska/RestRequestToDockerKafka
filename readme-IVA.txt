
docker-compose up -d

d:\workspace2018\kafka_2.11-0.10.2.0\bin\windows>.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka_test_topic
d:\workspace2018\kafka_2.11-0.10.2.0\bin\windows>.\kafka-console-producer.bat --broker-list localhost:9092 --topic kafka_test_topic


PowerShell . admin
docker attach 51277f1c2aa1
docker exec -i -t 51277f1c2aa1 /bin/sh
 nc -zv kafkaserver 9092
 
  docker stop $(docker ps -aq)
  docker rm $(docker ps -aq)
  
  docker network ls
  
  
c:\Windows\System32\drivers\etc\hosts  append   to use from local machine
127.0.0.1 kafka



https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/processor-samples/uppercase-transformer