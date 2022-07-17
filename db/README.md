# MongoDB Database

## Schema

### Author
```
{
    "_id": {
        "$oid": "string"
    },
    "author_id": int,
    "username": "string",
    "password": "string",
    "avatar_filename": "string",
    "organization": "string",
    "date_created": "date",
    "works": [int],
    "favorites": [int],
    "following": [int],
    "follower": [int],
}
```

### Work
```
{
    "_id": {
        "$oid": "string"
    },
    "work_id": int,
    "author_id": int,
    "type": int,
    "filename": "string",
    "date_created": "date",
    "comments": [int],
    "likes": [int],
    "dislikes": [int],
}
```

### Comment
```
{
    "_id": {
        "$oid": "string"
    },
    "comment_id": int,
    "content": "string",
    "children_id": [int],
    "date_created": "date",
    "likes": [int],
    "dislikes": [int],
}
```

## Tips on Running MongoDB locally on Mac
### Installation
```
sudo chown -R $(whoami) $(brew --prefix)/*

brew tap mongodb/brew

brew install mongodb-community@4.2

brew services start mongodb-community@4.2

ps aux | grep -v grep | grep mongod
```

### Export Path to MongoDB directory
```
export PATH="$PATH:/usr/local/opt/mongodb-community@4.2/bin"
```
### Path to MongoDB directory
```
cd /usr/local/opt/mongodb-community@4.2/bin
```

### DB Path
```
db.adminCommand("getCmdLineOpts")
```

### Resolve MongoDB Error 100
Go to the MongoDB directory, then run
```
./mongod --dbpath ./data/db
```

### Resolve Failed to unlink socket file
Problem
```
[initandlisten] Failed to unlink socket file /tmp/mongodb-27017.sock Permission denied
```
Run
```
sudo rm /tmp/mongodb-27017.sock
```

### Resolve MongoDB Error 48
Run
```
lsof -i | grep 27017
kill <PID>
```

