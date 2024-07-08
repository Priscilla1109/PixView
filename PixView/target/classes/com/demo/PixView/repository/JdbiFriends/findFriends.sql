SELECT u.* FROM USERS u
JOIN FRIENDS uf ON u.user_id = uf.friend_id
WHERE uf.user_id = :userId;