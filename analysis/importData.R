#read in data from file and produce tables, graphs, etc.
#setwd(".../output")
filenames = list.files(".");
for(i in 1:length(filenames)){
	assign(filenames[i], read.csv(filenames[i],header=FALSE));
}
#will need minority and white connections for each grade level (ex b1, w1, b2, w2, etc...)
b1Vec = c()
b2Vec = c()
b3Vec = c()
b4Vec = c()
w1Vec = c()
w2Vec = c()
w3Vec = c()
w4Vec = c()
bConnectsVec=c()
wConenctsVec=c()
for(i in 1:length(filenames)){
	#get minority freshmen's connections
	b1Vec = c(b1Vec, get(filenames[i])[get(filenames[i])$V2=="b"&get(filenames[i])$V3=="year1","V4"])
	#get minority sophomores' connections
	b2Vec = c(b2Vec, get(filenames[i])[get(filenames[i])$V2=="b"&get(filenames[i])$V3=="year2","V4"])
	#get minority juniors' connections
	b3Vec = c(b3Vec, get(filenames[i])[get(filenames[i])$V2=="b"&get(filenames[i])$V3=="year3","V4"])
	#get minority seniors' connections
	b4Vec = c(b4Vec, get(filenames[i])[get(filenames[i])$V2=="b"&get(filenames[i])$V3=="year4","V4"])
	#get white freshmen's connections
	w1Vec = c(w1Vec, get(filenames[i])[get(filenames[i])$V2=="w"&get(filenames[i])$V3=="year1","V4"])
	#get white sophomoress connections
	w2Vec = c(w2Vec, get(filenames[i])[get(filenames[i])$V2=="w"&get(filenames[i])$V3=="year2","V4"])
	#get white juniors' connections
	w3Vec = c(w3Vec, get(filenames[i])[get(filenames[i])$V2=="w"&get(filenames[i])$V3=="year3","V4"])
	#get white seniors' connections
	w4Vec = c(w4Vec, get(filenames[i])[get(filenames[i])$V2=="w"&get(filenames[i])$V3=="year4","V4"])
	
}

#display all hists
par(mfrow=c(4,2))
hist(b1Vec, breaks=25)
hist(w1Vec, breaks=25)
hist(b2Vec, breaks=25)
hist(w2Vec, breaks=25)
hist(b3Vec, breaks=25)
hist(w3Vec, breaks=25)
hist(b4Vec, breaks=25)
hist(w4Vec, breaks=25)



