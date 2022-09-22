## Cache Simulator
The driver code was provided by the professor, and I only worked on the implementation. I included only the file that I worked on. Thus, some things might not make full sense with the missing context.

The project was coded, compiled, and ran in a bash shell. Instead of providing screenshots, I instead write the output:

The usage for the program: <br/>
`usage: [-r lru|mru|rand] [-s seed] [-v] s-E-b-m` <br/>
`where s-E-b-m specified cache parameters:` <br/>
`  s: # of bits in address used to specify set` <br/>
`  E: # of cache lines per set` <br/>
`  b: # of bits in address used to specify offset in cache line` <br/>
`  m: total # of bits used to address primary memory` <br/>
`  must have all non-negative and 2 <= b and b + s < m` <br/>

Where the provided parameters are then passed to a new cache simulation.