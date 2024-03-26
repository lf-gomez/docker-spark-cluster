# Pyspark Demo

In this demo, we are reading the top spotify songs dataset from kaggle ([download here](https://www.kaggle.com/datasets/arnavvvvv/spotify-music/code)), 
retrieving the number of popular songs released each month and the top 5 artists with most popular songs.

## Run demo

Connect to a master or worker container:

```bash
docker exec -it <CONTAINER_ID> bash
```

Submit main file:

```bash
/opt/spark/bin/spark-submit --master spark://spark-master:7077 /opt/spark-apps/pyspark_demo/main.py
```
