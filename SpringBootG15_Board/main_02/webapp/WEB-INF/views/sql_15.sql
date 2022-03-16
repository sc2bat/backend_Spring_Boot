SELECT * FROM member;

SELECT * FROM board;

SELECT ROWNUM AS rn, b.* FROM board b ORDER BY num DESC;


SELECT board_seq.nextVal, board_seq.currVal FROM dual;
SELECT reply_seq.nextVal, reply_seq.currVal FROM dual;

SELECT * FROM reply;


UPDATE board SET readcount=readcount+1 WHERE num=15;



















