% Find the first ten positive values of x for which x = tan x.

format long;
f = @(x) tan(x) - x;
fprime = @(x) (tan(x))^2;
A = zeros([1,10]);
for n = 0:9
    x = n*pi + pi/2 - 0.05;
    k = 0;
    xprev = 0;
    while abs(x - xprev) > eps*abs(x)
        xprev = x;
        x = x - f(x)/fprime(x);
        k = k + 1;
    end
    A(n+1) = x;
end
A

x = 0:0.001:35;
y = f(x);
plot(x,y,'-',A,0,'or');
ylim([-50 50]);
line([0 35], [0 0], 'color','black');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Here is a table of the distance, d, that a hypothetical vehicle requires to stop
% if the brakes are applied when it is traveling with velocity v
% v(m/s)  d(m)
% 0         0
% 10        5
% 20        20
% 30        46
% 40        70
% 50        102
% 60        153
% What is the speed limit for this vehicle if it must be able to stop in at most
% 60 m? Compute the speed three different ways.
% (a) piecewise linear interpolation,
% (b) piecewise cubic interpolation with pchiptx,
% (c) reverse piecewise cubic interpolation with pchiptx.
% Because these are well-behaved data, the three values are close to each other,
% but not identical.

clear all;
clc;
format long;
v = [ 0 10 20 30 40 50 60 ];
d = [ 0 5 20 46 70 102 153];
xx = 0:0.001:160;
piecewiseLinear = fzero(@(xx) piecelin(v,d,xx) - 60, 60)

piecewiseCubic = fzero(@(xx) pchiptx(v,d,xx) - 60, 60)

piecewiseCubicReverse = fzero(@(xx) pchiptx(d, v, 60) - xx, 60)

y = piecelin(v,d,xx);
yy = pchiptx(v,d,xx);

plot(xx,y,'-b',xx,yy,'-g');
line([0 160], [0 0], 'color','black');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Kepler’s model of planetary orbits includes a quantity E, the eccentricity
% anomaly, that satisfies the equation
% M = E − e sin E,
% where M is the mean anomaly and e is the eccentricity of the orbit. For this
% exercise, take M = 24.851090 and e = 0.1.
% (a) Use fzerotx to solve for E. You can assign the appropriate values to M
% and e and then use them in the definition of a function of E.
% M = 24.851090
% e = 0.1
% F = @(E) E - e*sin(E) - M
% Use F for the first argument to fzerotx.
% (b) An “exact” formula for E is known:
% E = M + 2 sum((m = 1:inf)1/m*Jm*(me)*sin(mM))
% where J m (x) is the Bessel function of the first kind of order m. Use this
% formula, and besselj(m,x) in Matlab , to compute E. How many terms
% are needed? How does this value of E compare to the value obtained with
% fzerotx?

clear all; clc;
format long;

M = 24.851090;
e = 0.1;
F = @(E) E - e*sin(E) - M;
E_fzerotx = fzerotx(F, [20 30])

M = 24.851090;
e = 0.1;
m = 1;
P = @(m) (1/m * besselj(m,m*e) * sin(m*M));
while P(m) ~= 0
    if m == 1
        sum = P(m);
    else
        sum = sum + P(m);
    end
    m = m + 1;
end
E_exact = M + 2 * sum
iterations = m - 1

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Utilities must avoid freezing water mains. If we assume uniform soil condi-
% tions, the temperature T (x, t) at a distance x below the surface and time t
% after the beginning of a cold snap is given approximately by
% (T(x,t) - Ts) / (Ti - Ts) = erf(x/(2*sqrt(at))
% Here T s is the constant surface temperature during the cold period, T i is the
% initial soil temperature before the cold snap, and α is the thermal conductivity
% of the soil. If x is measured in meters and t in seconds, then α = 0.138 ·
% 10 −6 m 2 /s. Let T i = 20 ◦ C, and T s = −15 ◦ C, and recall that water freezes at
% 0 ◦ C. Use fzerotx to determine how deep a water main should be buried so
% that it will not freeze until at least 60 days’ exposure under these conditions.

clear all; clc;
format long;

T_i = 20;
T_s = -15;
T_x_t = 0;
t = 5184000;
a = 0.138 * 10^-6;
T = @(x) erf(x / (2 * sqrt(a * t)) ) * (T_i - T_s) + T_s - T_x_t;

depth = fzerotx(T,[0 1])

x = 0:0.01:5;
y = T(x);
plot(x,y,'-',depth, 0, 'o');
line([0 5], [0 0], 'color','black');