package com.rememberwhen.app.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rememberwhen.app.model.EraItem;

// Calls the TMDB API to fetch movies matching a given birth year, and turns them into EraItem objects
@Service
public class EraService {

    // Reads the API key we stored in application.properties - never hardcoded here
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<EraItem> getMoviesByYear(int year, String country) throws Exception {
        List<EraItem> results = new ArrayList<>();

        // TMDB's "discover" endpoint lets us filter movies by exact release year
        String url = "https://api.themoviedb.org/3/discover/movie"
                + "?api_key=" + tmdbApiKey
                + "&primary_release_year=" + year
                + "&sort_by=popularity.desc";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the JSON response into a tree we can navigate
        JsonNode root = objectMapper.readTree(response.body());
        JsonNode movies = root.get("results");

        if (movies != null && movies.isArray()) {
            // Take the top 10 most popular movies from that year
            int count = 0;
            for (JsonNode movie : movies) {
                if (count >= 10) break;

                String title = movie.get("title").asText();
                String posterPath = movie.has("poster_path") && !movie.get("poster_path").isNull()
                        ? movie.get("poster_path").asText()
                        : null;

                // TMDB only gives us a partial image path - we build the full URL ourselves
                String fullImageUrl = posterPath != null
                        ? "https://image.tmdb.org/t/p/w500" + posterPath
                        : null;

                double popularity = movie.has("popularity") ? movie.get("popularity").asDouble() : 0.0;

                EraItem item = new EraItem(title, "Movie", year, country, fullImageUrl, popularity);
                results.add(item);
                count++;
            }
        }

        return results;
    }
}