package ConnectDB;
import com.mongodb.client.*;
import org.bson.Document;
import Login.*;

public class Connect {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoCursor<Document> cursor;
    private Hashing hashing = new Hashing();

    public Connect(){
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("javaCk");
        System.out.println("Connected to database successfully");
    }

    public boolean checkLogin(String username, String password){
        collection = database.getCollection("auths");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String user = document.getString("username");
            String pass = document.getString("password");
            String hashedPass = hashing.passwordHashing(password);
            if(user.equals(username) && pass.equals(hashedPass)){
                return true;
            }
        }
        return false;
    }
    public boolean checkExistUserName(String username){
        collection = database.getCollection("auths");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String user = document.getString("username");
            if(user.equals(username)){
                return true;
            }
        }
        return false;
    }
    public void registerAccount(String username,String password,String question,String answer,boolean taken){

        collection = database.getCollection("auths");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String user = document.getString("username");
            if(user.equals(username)){
                taken = true;
                return;
            }
        }
        if(taken == false){
            String hashedPass = hashing.passwordHashing(password);
            String hashedAnswer = hashing.passwordHashing(answer);
            Document document = new Document("username",username)
                    .append("password",hashedPass)
                    .append("question",question)
                    .append("answer",hashedAnswer);
            collection.insertOne(document);
        }
    }

    public boolean checkUser(String username, String question, String answer){
        collection = database.getCollection("auths");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String user = document.getString("username");
            String ques = document.getString("question");
            String ans = document.getString("answer");
            String hashedAns = hashing.passwordHashing(answer);
            if(user.equals(username) && ques.equals(question) && ans.equals(hashedAns)){
                return true;
            }
        }
        return false;
    }

    public void changePassword(String username,String password){
        collection = database.getCollection("auths");
        String hashedPass = hashing.passwordHashing(password);
        collection.updateOne(new Document("username",username),new Document("$set",new Document("password",hashedPass)));
    }
}
