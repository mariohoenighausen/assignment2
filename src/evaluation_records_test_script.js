db = connect('mongodb://localhost:27017/evaluation_records_test');
db.createCollection("salesman");
db.salesman.insertOne({sid:1,firstName:"Peter",lastName:"Griffin",dob:"20.08.1995",experience:"CEO Erfahrungen"});
db.createCollection("evaluation_record");
db.evaluation_record.insertOne({erid:1,actual_value:850,year:2021,sid:1,goalID:1});

db.createCollection("goal");
db.goal.insertOne({goalID:1,goal_description:"Sell useless stuff",target_Value:1000});
db.performance_records.find({"sid":{$lt: 2} });
db.drop("salesmen");