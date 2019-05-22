package com.example.storm;

import com.example.storm.bolt.ExclaimBasicBolt;
import com.example.storm.bolt.PrintBolt;
import com.example.storm.spout.RandomSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

public class BasicTopo {

    public static void main(String[] args) throws  Exception{
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new RandomSpout());
        builder.setBolt("excl", new ExclaimBasicBolt()).shuffleGrouping("spout");
        builder.setBolt("print", new PrintBolt()).shuffleGrouping("excl");

        Config config = new Config();
        config.setDebug(false);

       if (args != null && args.length > 0) {
           config.setNumWorkers(3);
           StormSubmitter.submitTopology(args[0], config, builder.createTopology());
       } else {
           LocalCluster cluster = new LocalCluster();
           cluster.submitTopology("test", config, builder.createTopology());
           Utils.sleep(100000);
           cluster.killTopology("test");
           cluster.shutdown();
       }

    }

}
