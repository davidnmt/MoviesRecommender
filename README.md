# MoviesRecommender
A Java program that mainly recommends movies for a user, based on closeness of this user ratings with other users.

This project uses ArrayList, HashMap, HashSet data structures. Static methods, Programming Oriented Object designs.

The program is divided into 4 main exercises, which corresponds to 4 main java files: FirstRatings.java, SecondRatings.java, ThirdRatings.java, FourthRatings.java.

The first main file, FirstRating.java. I use the provided classes Movie.java, Rating.java and Rater.java to read in from a CSV file and store information in an arrayList data structure about movies and ratings of movies by different movie raters. Questions that the program should answer is how many movies include the Comedy genre or how many movies are greater that 150 minutes in length. The class Movie is a Plain Old Java Object class for storing the data about one movie.

The second main file, SecondRating.java, I use the provided classes Movie.java, Rating.java and Rater.java and also the FirstRatings class to store information in an arrayList data structure about movies and ratings of movies by different movie raters. In this exercise, HashSet data structures, HashMap data structures, constructor and fields, private methods are used. Some of the questions that the programs should answer is: printing the list of movies and their average ratings, for all those movies that have at least a specified number of ratings.

The third main file, ThirdRating.java,  interfaces and filters are used to rewrite the existing code, make it more flexible and effcient. For example, filters to select a desired subset of movies that you want to recommend, such as "all movies under the two hours long" or "all movies made in 2012".

The fourth main file, FourthRating.java, I use a new java class to find users in the database, with the particularity that all its methods are static. In this exercise, those movie ratings which are close in ratings to other users will be evaluated. The complex of the algorithm recommends movies which are closest to another user rating.
