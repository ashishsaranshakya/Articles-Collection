import mongoose from 'mongoose';

mongoose.connect("mongodb+srv://admin-ashish:hotel9ervictor@web-test-projects.bwuoqdk.mongodb.net/articlesDB?retryWrites=true&w=majority", {useNewUrlParser: true, useUnifiedTopology: true});


const articleSchema = new mongoose.Schema ({
    title: String,
    content: String
  });

const Article = new mongoose.model("Article", articleSchema);


export {articleSchema, Article};