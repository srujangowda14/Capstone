const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('Client Routes', () => {

  it('should respond with a message for /client route', async () => {
    const res = await chai.request(app).get('/client');
    expect(res).to.have.status(200);
    expect(res.body).to.have.property('message').equal('Hello from /client route!');
  });


  it('should return success on successful login', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .post('/client/login')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(200); 
          expect(res.body).to.be.an('object');
        
        });
  });


  it('should return error for invalid email', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abcexample.com'
      };
  
      chai
        .request(app)
        .post('/client/login')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(500); 
          expect(res.body).to.eql({ clientId: 0, email: 'Invalid email', token: 0 })
        
        });
  });


  it('should return error for email unregistered', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc145@example.com'
      };
  
      chai
        .request(app)
        .post('/client/login')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(500); 
          expect(res.body).to.eql(
            { clientId: 0, email: "Email doesn't exist", token: 0 });
        
        });
  });

  it('should return error for incorrect clientId', async () => {
    const payload = {
        clientId: 9438421,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .post('/client/login')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(401)
          expect(res.body).to.eql({ clientId: 9438421, email: 'abc@example.com', token: 0 }); 
        
        
        });
  });

  it('should return error while registering an already existing user', async () => {
    const payload = {
        clientId: '',
        email: 'test123@gmail.com',
        postalCode: 789012,
        name: 'test',
        dateOfBirth: '2001-09-08',
        country: 'USA',
        identifications: [
          {
            type: 'SSN',
            value: '567891234'
          }
        ]
      };
  
      chai
        .request(app)
        .post('/client/register')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(500); 
          expect(res.body).to.be.an('object');
        
        });
  });

 

});