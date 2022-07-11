# RunWu - MongoDB Database Schema
## Author
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

## Work
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

## Comment
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