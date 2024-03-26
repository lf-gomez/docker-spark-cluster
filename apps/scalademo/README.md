# Scala Spark Demo

In this demo, we are reading the top spotify songs dataset from kaggle ([download here](https://www.kaggle.com/datasets/arnavvvvv/spotify-music/code)), 
retrieving the number of popular songs released each month and the top 5 artists with most popular songs.

## Run demo

Inside `apps/scalademo`, package a jar with the application:

```bash
sbt package
```

Connect to a master or worker container:

```bash
docker exec -it spark-master bash
```

Submit jar and run the `ScalaDemo` class:

```bash
/opt/spark/bin/spark-submit --master spark://spark-master:7077 --class "ScalaDemo" /opt/spark-apps/scalademo/target/scala-2.12/scala-spark-demo_2.12-1.0.jar
```
