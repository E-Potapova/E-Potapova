## Integer Set
The driver code was provided by the professor, and I only worked on the implementation. I included only the file that I worked on. Thus, some things might not make full sense with the missing context.

The project was coded, compiled, and ran in a bash shell. Instead of providing screenshots, I instead write the output:

Program usage: <br/>
`> ./int-set` <br/>
`usage:  ./int-set set SET` <br/>
`        ./int-set union SET SET` <br/>
`        ./int-set intersection SET SET`<br/>
`where SET is a brace-enclosed comma-separated list of ints`<br/>

Creating a set (ordered, unique items): <br/>
`> ./int-set set "{3,3,4,2,7,5,3}"`<br/>
`{ 2, 3, 4, 5, 6, 7 }`

Getting the union of two sets: <br/>
`> ./int-set union "{2,3,3,4,5,2}" "{1,1,0,3,6,3,4}"`<br/>
`{ 0, 1, 2, 3, 4, 5, 6 }`

Getting the intersection of two sets: <br/>
`> ./int-set union "{2,3,3,4,5,2}" "{1,1,0,3,6,3,4}"`<br/>
`{ 3, 4 }`

Although not provided here, the professor created many unit tests to thoroughly test the implementation: <br/>
`Running suite(s): IntSet Tests` <br/>
` newIntSet`<br/>
` addIntSet`<br/>
` nElementsIntSet`<br/>
` addMultipleIntSet`<br/>
` isInIntSet`<br/>
` iterator`<br/>
` sscanIntSet`<br/>
` snprintIntSet`<br/>
` unionIntSet`<br/>
` intersectionIntSet`<br/>
`100%: Checks: 38, Failures: 0, Errors: 0`