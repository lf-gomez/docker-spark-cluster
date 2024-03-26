/* SimpleApp.scala */
import org.apache.spark.sql.functions.explode
import org.apache.spark.sql.{SparkSession, functions}

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Scala Spark Demo").getOrCreate()

    import spark.implicits._

    val data = spark
      .read
      .option("header", "true")
      .csv("/opt/spark-data/demo/Popular_Spotify_Songs.csv")

    val popular_songs_by_date_count = data
      .groupBy($"released_year", $"released_month")
      .count()
      .sort($"released_year", $"released_month")
      .collect()

    val top_5_artists_with_most_popular_songs = data
      .withColumn("artist", explode(functions.split($"artist(s)_name", ", ")))
      .groupBy($"artist")
      .count()
      .withColumnRenamed("count", "num_songs")
      .sort($"num_songs".desc)
      .take(5)

    println(popular_songs_by_date_count.mkString("Array(", ", ", ")"))
    println(top_5_artists_with_most_popular_songs.mkString("Array(", ", ", ")"))

    spark.stop()
  }
}