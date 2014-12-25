function trap = trapazoidRule(n, a, b, f)
    sum = 0;
    h = (b-a)/(n-1);
    for k = 1:n-2
        sum = sum + f(a + k*h);
    end
    trap = (h/2)*f(a) + h*sum + (h/2)*f(b);
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all; clc; close all;
format long;

% Function 1 variables
% f_exact = pi;
% f = @(x) 2/(1+x^2);

% Function 2 variables
% f_exact = pi/2;
% f = @(x) 1/(1+x^2);

% Function 3 variables
% f_exact = 5 * sqrt(pi) * erf(1/5);
% f = @(x) exp(-(x/5)^2);

% Function 4 variables
f_exact = 1/5 * sqrt(pi) * erf(5);
f = @(x) exp(-(5*x)^2);

for n = 1:27
    trap = trapazoidRule(2^n, -1, 1, f);
    error = abs(f_exact - trap);
    plot(log2(2^n),log10(error), 'ro');
    hold on;
end