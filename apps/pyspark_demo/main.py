from pyspark.sql import SparkSession
from pyspark.sql.functions import explode, split

sess = SparkSession.builder.appName("Pyspark Demo").getOrCreate()

# Base dir: ./data/ => /opt/spark-data
data = sess \
    .read \
    .option("header", "true") \
    .csv("/opt/spark-data/demo/Popular_Spotify_Songs.csv")

popular_songs_by_date_count = data \
    .groupby(["released_year", "released_month"]) \
    .count() \
    .sort("released_year", "released_month") \
    .collect()

top_5_artists_with_most_popular_songs = data \
    .withColumn("artist", explode(split("artist(s)_name", ", "))) \
    .groupby(["artist"]) \
    .count() \
    .withColumnRenamed("count", "num_songs") \
    .sort("num_songs", ascending=False) \
    .take(5)

print(popular_songs_by_date_count)
print(top_5_artists_with_most_popular_songs)
