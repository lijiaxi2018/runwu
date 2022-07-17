db = db.getSiblingDB('userdata')

db.createCollection("author")
db.createCollection("work")
db.createCollection("comment")