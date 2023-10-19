import { Instrument } from "./instrument";

export class LivePricing {
        constructor(
            public askPrice: number,
            public bidPrice: number,
            public priceTimestamp: string,
            public instrument: Instrument        
        ){}
        
}
