package org.iconsider.taxi.driver;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.iconsider.taxi.job.TestJob;
import scala.Serializable;
import scala.Tuple2;

public class TestDriver implements Serializable {
    private static final long serialVersionUID = 8958537956400291346L;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();

        conf.setAppName("test-app")
                .setMaster("local")
                .set("spark.executor.memory", "600m")
                .set("spark.total.executor.cores", "2")
                .setJars(new String[] {"Z:\\jar\\nyc-taxi.jar"});

        Tuple2<String, String>[] configs = conf.getAll();
        for (Tuple2<String, String> config : configs) {
            System.out.println(String.format("%s -> %s", config._1, config._2));
        }


        JavaSparkContext context = new JavaSparkContext(conf);
        TestJob testJob = new TestJob();
        testJob.execute(context);

    }
}
