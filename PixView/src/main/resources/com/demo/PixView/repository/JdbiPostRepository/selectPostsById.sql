-- Recupera todos os POSTS de um USER específico, além da contagem total de likes que teve
SELECT p.*, (SELECT COUNT(*) FROM LIKES l WHERE l.post_id = p.post_id) AS total_likes
FROM POSTS p
WHERE p.post_id = :postId
ORDER BY p.local_date_time DESC;