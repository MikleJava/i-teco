package hazelcast;

import com.hazelcast.core.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainHazelcastTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Map<String, Integer> map = hz.getMap("map");
        for (int i = 0; i < 500; i++) {
            map.put(UUID.randomUUID().toString(), 1);
        }
        IExecutorService executor = hz.getExecutorService("executor");
        Map<Member, Future<Integer>> result = executor.submitToAllMembers(new SomeTaskTest());
        int sum = 0;
        for(Future<Integer> future : result.values()) {
            sum += future.get();
        }
        System.out.println("Result " + sum);
    }
}
