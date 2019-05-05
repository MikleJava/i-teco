package hazelcast;

import com.hazelcast.core.Hazelcast;

public class SlaveHazelcast {
    public static void main(String[] args) {
        Hazelcast.newHazelcastInstance();
    }
}
