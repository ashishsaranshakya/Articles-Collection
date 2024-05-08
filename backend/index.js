import express from 'express';
import bodyParser from 'body-parser';
import {Article} from './model.js';

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/articles', (req, res) => {
    Article.find({})
        .then(result=>res.send(result))
        .catch(err=>res.sendStatus(404));
});

app.get('/article/title/:title', (req, res) => {
    Article.findOne({title:req.params.title})
        .then(result=>res.send(result))
        .catch(err=>res.sendStatus(404));
});

app.get('/article/id/:id', (req, res) => {
    Article.findOne({_id:req.params.id})
        .then(result=>res.send(result))
        .catch(err=>res.sendStatus(404));
});

app.post('/article', (req, res) => {
    const newArticle = new Article({
        title: req.body.title,
        content: req.body.content
    });
    console.log(req.body);
    newArticle.save()
        .then(result=>res.sendStatus(201))
        .catch(err=>res.sendStatus(500));
})

app.put('/article/title/:title', (req,res)=>{
    Article.findOne({title:req.params.title})
        .then(result=>{
            result.title=req.body.title;
            result.content=req.body.content;
            result.save()
            .then(r=>res.sendStatus(200))
            .catch(e=>res.sendStatus(500));
        })
        .catch(err=>res.sendStatus(404));
})

app.put('/article/id/:id', (req,res)=>{
    Article.findOne({_id:req.params.id})
        .then(result=>{
            result.title=req.body.title;
            result.content=req.body.content;
            result.save()
            .then(r=>res.sendStatus(200))
            .catch(e=>res.sendStatus(500));
        })
        .catch(err=>res.sendStatus(404));
})

app.patch('/article/title/:title', (req,res)=>{
    Article.findOne({title:req.params.title})
        .then(result=>{
            if(!!req.body.title){
                result.title=req.body.title;
            }
            else if(!!req.body.content){
                result.content=req.body.content;
            }
            result.save()
            .then(r=>res.sendStatus(200))
            .catch(e=>res.sendStatus(500));
        })
        .catch(err=>res.sendStatus(404));
})

app.patch('/article/id/:id', (req,res)=>{
    Article.findOne({_id:req.params.id})
        .then(result=>{
            if(!!req.body.title){
                result.title=req.body.title;
            }
            else if(!!req.body.content){
                result.content=req.body.content;
            }
            result.save()
            .then(r=>res.sendStatus(200))
            .catch(e=>res.sendStatus(500));
        })
        .catch(err=>res.sendStatus(404));
})

app.delete('/articles', (req, res)=>{
    Article.delete({})
    .then(r=>res.sendStatus(200))
    .catch(e=>res.sendStatus(500));
})

app.delete('/article/title/:title', (req, res) => {
    Article.deleteOne({title:req.params.title})
        .then(result=>res.sendStatus(200))
        .catch(err=>res.sendStatus(404));
});

app.delete('/article/id/:id', (req, res) => {
    Article.deleteOne({_id:req.params.id})
        .then(result=>res.sendStatus(200))
        .catch(err=>res.sendStatus(404));
});

app.listen(3000, () => {
    console.log('Server started on port 3000');
});