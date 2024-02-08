// This test file contains erreurs to be spotted by tslint.

// Invalid variable name (should use camelCase)
var My_Variable = 10;

// Unused variable
var unusedVariable: string;

// Missing semicolon
console.log("Hello, world!")

// Indentation issue
if (true) {
console.log("Indentation issue");
}

// Unused import
import { unusedImport } from 'unused';

// Function with too many parameters
function exampleFunction(parameter1: string, parameter2: number, parameter3: boolean) {
    // Function body
}

// Incorrect string quotation
const incorrectQuote = "This is incorrect";

// Invalid type assignment
let myNumber: string = 10;

// Function with no return type annotation
function add(a: number, b: number) {
    return a + b;
}
