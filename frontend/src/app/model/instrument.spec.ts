import { Instrument } from './instrument';

describe('Instrument', () => {
  it('should create an instance', () => {
    expect(new Instrument('','','','','',1,1000)).toBeTruthy();
  });
});
