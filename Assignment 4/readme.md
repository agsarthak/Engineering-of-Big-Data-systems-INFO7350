For this week, you are going to get ready for delivering the homework for the mondayafternext, where you are going to find the most popular species on wkispeciesusing the PageRank algorithm
–But you are still going to submit homework this week
–Just that your results need not be as polished, yet

•Download wikispeciesstraight from wikimediaor use the compressed XML on blackboard

•To do this, you are going to have to mapreducewith the XML data type. Since building hadoopdata types is not super easy, i'llgive this type to you next week.

•For now, you can start with the smaller, dataset on blackboard, that is already shardedto be in text format, one line per record

•Iterate until you see that the PageRank deltas get smaller, you don't have to converge completely. If, for some reason, you see junk appearing in the data, get rid of it in your code

•Chain your mapreduceswith a loop in your driver


What to turn in

•Run in standalone mode: Email Hadoop output file(s), graphs, report
–Are PageRanksconverging? Yay!
–Are they diverging? Why?

•Run in pseudo-distributed mode

–Email jobtracker’sand HDFS status pages:
•http://localhost:50070
•http://localhost:50030–and Hadoop output files, and report
