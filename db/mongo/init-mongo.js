db.createUser({
    user: 'root',
    pwd: 'toor',
    roles: [
        {
            role: 'readWrite',
            db: 'testDB',
        },
    ],
});
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    {
        "username": "ragnar777",
        "dni": "VIKI771012HMCRG093",
        "enabled": true,
        "password": "$2a$10$CFDkmLL6tf.FbcDHkxByr.dGdnjJmSY96RVSzP1JsLc/IyFSnM.r2",
        "role":
            {
                "granted_authorities": ["read"]
            }
    },
    {
        "username": "heisenberg",
        "dni": "BBMB771012HMCRR022",
        "enabled": true,
        "password": "$2a$10$MDSZXc4eKuVlZz0c/654B.LA48DbfJJyeRo9Dpa6rsDgghFonfrUS",
        "role":
            {
                "granted_authorities": ["read"]
            }
    },
    {
        "username": "misterX",
        "dni": "GOTW771012HMRGR087",
        "enabled": true,
        "password": "$2a$10$dylt9IpmBiIpLUiJD6EKX.Sr2ihptAKExBqVM8I7qoKaj071.Og2K",
        "role":
            {
                "granted_authorities": ["read", "write"]
            }
    },
    {
        "username": "neverMore",
        "dni": "WALA771012HCRGR054",
        "enabled": true,
        "password": "$2a$10$OcbAfJrDLDn7Roj1dHg8yuHhHEd/dRAi32XWY7tYiB.Xukt1QGVW.",
        "role":
            {
                "granted_authorities": ["write"]
            }
    }
]);