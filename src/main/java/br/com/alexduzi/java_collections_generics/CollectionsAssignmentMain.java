package br.com.alexduzi.java_collections_generics;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsAssignmentMain {
    public static void main(String[] args) {
//        Given:
//        a map that maps a channel name to it’s number of subscribers
//        another map that maps a channel name to its publisher
//        generate a map that maps the publisher to its number of subscribers.

        Map<String, Integer> channelToSubscribers
                = new TreeMap<>(); // channelName, numSubscribers
        Map<String, String> channelToPublisher
                = new TreeMap<>(); // channelName, publisher
        Map<String, Integer> publisherToSubscribers
                = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers (K, V1)
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher (K, V2)
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

//        Algorithm:
//        1. Using a forEach(BiConsumer), set up the publisherToSubscribers map.
//        2. Using a forEach(BiConsumer), output publisherToSubscribers.
//        3. Calculate the publisher with the most and least subscribers. Collections.min() and
//        Collections.max() can be useful here.4

        channelToSubscribers.forEach((k, v) -> {
            if (channelToPublisher.containsKey(k)) {
                String key = channelToPublisher.get(k);
                publisherToSubscribers.merge(key, v, Integer::sum);
            }
        });

        publisherToSubscribers.forEach((k, v) -> {
            System.out.printf("Publisher : %s \t Subscribers: %d\n", k, v);
        });

        int maxSub = Collections.max(publisherToSubscribers.values());
        int minSub = Collections.min(publisherToSubscribers.values());

        publisherToSubscribers.forEach((k, v) -> {
            if (maxSub == v) {
                System.out.println("Publisher with most subscribers: " + k + " " + v);
            } else if (minSub == v) {
                System.out.println("Publisher with fewest subscribers: " + k + " " + v);
            }
        });
    }
}
