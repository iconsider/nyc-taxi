package org.iconsider.taxi.job;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.io.Serializable;

public class TestJob implements Serializable {
    private static final long serialVersionUID = -113184886409674464L;

    public void execute(JavaSparkContext context) {
        JavaRDD<String> lines = context.textFile("hdfs://hadoop1:9000/user/root/taxi/green_tripdata_2016-10.csv");

        lines.mapToPair(new PairFunction<String, Integer, Integer>() {
            public Tuple2<Integer, Integer> call(String s) throws Exception {
                Tuple2 tuple = new Tuple2("line", 1);
                return tuple;
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer i1, Integer i2) throws Exception {
                return i1 + i2;
            }
        }).foreach(new VoidFunction<Tuple2<Integer, Integer>>() {
            public void call(Tuple2<Integer, Integer> result) throws Exception {
                System.out.println(String.format("key: %s, value: %s", result._1, result._2));
            }
        });


        System.out.println("done");
    }
}
