CREATE 
    (gravara:User {userId: 1, name: 'Milos Gravara'}),
    (kalina:User {userId: 2, name: 'Nikola Kalinic'}),
    (vukoman:User {userId: 3, name: 'Vladimir Vukoman'}),
    (lunic:User {userId: 4, name: 'Vladimir Lunic'}),
    (spira:User {userId: 5, name: 'Nikola Spiric'}),
    (vuk:User {userId: 6, name: 'Vuk Milanovic'}),
    (ilija:User {userId: 7, name: 'Ilija Galin'}),
    (andrija:User {userId: 8, name: 'Andrija Stanisic'}),
    (impresija:Accommodation {accommodationId: 1, name: 'Vila Impresija'}),
    (depresija:Accommodation {accommodationId: 2, name: 'Vila Depresija'}),
    (ravijojla:Accommodation {accommodationId: 3, name: 'Vila Ravijojla'}),
    (vukman:Accommodation {accommodationId: 4, name: 'Vila Vukman'}),
    (gruborka:Accommodation {accommodationId: 5, name: 'Vila Gruborka'}),
    (elena:Accommodation {accommodationId: 6, name: 'Vila Elena'}),
    (plandiste:Accommodation {accommodationId: 7, name: 'Vila Plandiste'}),
    (alibegovac:Accommodation {accommodationId: 8, name: 'Vila Alibegovac'}),
    (dedinje:Accommodation {accommodationId: 9, name: 'Vila Dedinje'}),
    (srbija:Accommodation {accommodationId: 10, name: 'Vila Srbija'}),
    (zlatibor:Accommodation {accommodationId: 11, name: 'Vila Zlatibor'}),
    (romanija:Accommodation {accommodationId: 12, name: 'Vila Romanija'}),
    (vila:Accommodation {accommodationId: 13, name: 'Vila Vila'}),
    (gravara)-[:STAYED_IN]->(impresija),
    (vukoman)-[:STAYED_IN]->(impresija),
    (lunic)-[:STAYED_IN]->(impresija),
    (andrija)-[:STAYED_IN]->(impresija),
    (kalina)-[:STAYED_IN]->(impresija),
    (gravara)-[:RATED {value: 4}]->(impresija),
    (kalina)-[:RATED {value: 5}]->(impresija),
    (vukoman)-[:RATED {value: 2}]->(impresija),
    (andrija)-[:RATED {value: 3}]->(impresija),
    (lunic)-[:RATED {value: 3}]->(impresija),
    (gravara)-[:STAYED_IN]->(depresija),
    (vukoman)-[:STAYED_IN]->(depresija),
    (lunic)-[:STAYED_IN]->(vukman),
    (andrija)-[:STAYED_IN]->(gruborka),
    (vukoman)-[:STAYED_IN]->(gruborka),
    (vuk)-[:STAYED_IN]->(gruborka),
    (lunic)-[:STAYED_IN]->(gruborka),
    (ilija)-[:STAYED_IN]->(gruborka),
    (kalina)-[:STAYED_IN]->(alibegovac),
    (kalina)-[:STAYED_IN]->(vukman),
    (kalina)-[:STAYED_IN]->(gruborka),
    (kalina)-[:STAYED_IN]->(vila),
    (kalina)-[:STAYED_IN]->(romanija),
    (kalina)-[:STAYED_IN]->(zlatibor),
    (kalina)-[:RATED {value: 2}]->(alibegovac),
    (kalina)-[:RATED {value: 2}]->(gruborka),
    (kalina)-[:RATED {value: 5}]->(vukman),
    (kalina)-[:RATED {value: 5}]->(romanija),
    (kalina)-[:RATED {value: 5}]->(zlatibor),
    (andrija)-[:RATED {value: 2}]->(gruborka),
    (vukoman)-[:RATED {value: 2}]->(gruborka),
    (lunic)-[:RATED {value: 5}]->(gruborka),
    (ilija)-[:RATED {value: 2}]->(gruborka),
    (vuk)-[:RATED {value: 2}]->(gruborka)


MATCH (a:Accommodation)<-[:STAYED_IN]-(u:User {name: 'Milos Gravara'})-[r1:RATED]->(a)<-[r2:RATED]-(otherUser:User)
WHERE r1.value = r2.value OR r1.value - 1 = r2.value OR r1.value + 1 = r2.value
RETURN a, otherUser

MATCH (u:User)-[:STAYED_IN]->(a:Accommodation)<-[r:RATED]-(u)
WHERE u.userId IN [1, 2] AND r.value IN [4, 5]
RETURN a


proceduralno isfiltrirati 