db = connect('mongodb://localhost:27017/evaluation_records_test');
// use evaluation_records_test;

db.createCollection("salesman");
db.salesman.insertOne({sid:1,firstName:"Peter",lastName:"Griffin",dob:"20.08.1995",experience:"CEO Erfahrungen"});
db.createCollection("evaluation_record");
db.evaluation_record.insertOne({erid:1,actualValue:850,targetValue:1000,year:2021,goalDescription:"Being the best Pokémon trainer",sid:1});

db.salesman.find()
db.evaluation_record.find()

db.evaluation_record.find({"sid":{$lt: 2} });
//db.drop("salesman");
//db.drop("evaluation_record");
//db.drop("goal")