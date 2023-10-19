const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('Trade Routes', () => {

  it('should respond with a message for /trade route', async () => {
    const res = await chai.request(app).get('/trade');
    expect(res).to.have.status(200);
    expect(res.body).to.have.property('message').equal('Hello from /trade route!');
  });


  it('should return success on successful trade', async () => {
    const payload = {
        instrumentId: "N123456",
        quantity: 1,
        targetPrice: 104.25,
        direction: "B",
        clientId: 943842911,
        orderId: "X",
        email: "abc@example.com",
        token: 943719455,
        instrumentDescription: "JP Morgan"
    }
  
      chai
        .request(app)
        .post('/trade/performTrade')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(200); 
          expect(res.body).to.be.an('object');
        
        });
  });


  it('should return error for invalid target price', async () => {
    const payload = {
        instrumentId: "N123456",
        quantity: 1,
        targetPrice: 10,
        direction: "B",
        clientId: 943842911,
        orderId: "X",
        email: "abc@example.com",
        token: 943719455,
        instrumentDescription: "JP Morgan"
    }
  
      chai
        .request(app)
        .post('/trade/performTrade')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(500); 
          expect(res.body).to.eql({
            message: 'Cannot invoke "java.util.Map.get(Object)" because "responseMap" is null',
            status: 500
          })
        
        });
  });


  it('should return error selling more than you have', async () => {
    const payload = {
        instrumentId: "N123456",
        quantity: 100,
        targetPrice: 10,
        direction: "S",
        clientId: 943842911,
        orderId: "X",
        email: "abc@example.com",
        token: 943719455,
        instrumentDescription: "JP Morgan"
    }
  
    chai
    .request(app)
    .post('/trade/performTrade')
    .send(payload)
    .end((err, res) => {
      expect(res).to.have.status(500); 
      expect(res.body).to.eql({ message: "Can't sell more than you hold", status: 500 })
    
    });
  });

  it('should return error for insufficient balance', async () => {
    const payload = {
        instrumentId: "N123456",
        quantity: 100,
        targetPrice: 10,
        direction: "B",
        clientId: 943842911,
        orderId: "X",
        email: "abc@example.com",
        token: 943719455,
        instrumentDescription: "JP Morgan"
    }
  
      chai
    .request(app)
    .post('/trade/performTrade')
    .send(payload)
    .end((err, res) => {
      expect(res).to.have.status(500); 
      expect(res.body).to.eql({ message: 'not enough balance', status: 500 })
    
    });
  });

 

});