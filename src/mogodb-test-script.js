db = connect('mongodb://localhost:27017/food');

db.createCollection("fruits");
db.fruits.insertMany([ {name: "apple", origin: "usa", price: 5}, {name: "orange", origin: "italy", price: 3}, {name: "mango", origin: "malaysia", price: 3} ]);
db.fruits.find().pretty();

//db.drop("food")