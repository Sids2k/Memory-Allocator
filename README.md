# Memory Allocator

## About
Java implementation of a memory allocator which takes in commands for allocating and freeing memory using best-fit and first-fit algorithms.

It maintains two data structures (AVL trees or BS trees or Doubly Linked Lists) to denote memory that has already been allocated elsewhere and memory that is free.

The data structure containing allocated memory has the starting address of the memory as the ```key```, whereas the data structure with the free memory contains the size of the memory block as the ```key```.

## Usage
Users can allocate memory of a certain size (Allocate) or free up memory starting at a certain address (Free) or combine all fragmented continous free memory blocks (Defragment).

### Format of giving commands

``` 
number of test cases

size

number of commands

command1

command2

...

size (next test case)

number of commands

command1

command2

...
```


### Format of commands
```Allocate Size```

```Free Address```

```Defragment -1```


## Requirements 
* java.util.*

* java.io.*


## Owner
Siddhant Sharma: https://github.com/Sids2k


## License
Copyright - 2020 - Siddhant Sharma

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
