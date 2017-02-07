data <- read.csv("C:/cygwin64/usr/local/hadoop-0.20.2/input_grouplens/ratings.csv")
data

library(magrittr)
library(dplyr)
library(plyr)
library(data.table)

data_df <- data.frame(data)
head(data_df)

data_table <- data.table(data_df)

aa <- as.data.table(data_table)[, toString(movieId), by = list(userId)]
head(aa)

write.csv(aa, file ="C:/cygwin64/usr/local/hadoop-0.20.2/input_grouplens/ratings_R_output.csv")
