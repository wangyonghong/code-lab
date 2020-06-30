db.createUser(
    {
        user: "test",
        pwd: "Passw0rd",
        roles: [
            {
                role: "readWrite",
                db: "springboot-lab"
            }
        ]
    }
);